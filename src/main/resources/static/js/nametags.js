var app = angular.module('nametags', ['ngRoute']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
    when('/events', {
        templateUrl: 'partials/list.html'
    }).
    when('/events/:eventId', {
        controller: 'members', templateUrl: 'partials/event.html'
    }).
    when('/raffle/:eventId', {
        controller: 'raffle', templateUrl: 'partials/raffle.html'
    }).
    otherwise({
        redirectTo: '/events'
    });
}]);

app.controller('events', function ($scope, $routeParams, $http) {
    $scope.events = [];
    $scope.selectedEvent = null;
    $scope.selectEvent = function (eventId) {
        $scope.selectedEvent = eventId;
    };

    $http.get('events/').success(function (data) {
        $scope.events = data;
    })
});

app.controller('members',
    function ($scope, $routeParams, $http) {
        $scope.eventId = $routeParams.eventId;
        $http.get('/attendees',
            { params: { 'eventId':$scope.eventId }}).success(function(data) {
            $scope.attendees = data.attendees;
        })
    });

app.controller('raffle',
    function ($scope, $routeParams, $http) {
        $scope.eventId = $routeParams.eventId;
        $scope.showRaffle = function() {
            var raffleDiv = $('#raffleDiv')
            var raffleTargets = $('.raffle')
            if(raffleDiv.is(':visible')) {
                raffleDiv.hide();
                raffleTargets.show();
            } else {
                raffleTargets.hide();
                raffleDiv.show();
            }
        }

        $scope.removeAttendee = function(attendee) {
            $scope.attendees.forEach(function(v,i) {
              if(attendee.id == v.id) {
                  $scope.attendees.splice(i, i+1)
              }
            })
            //do an ajax call
            $http.get('/raffle/remove', {
                params: {
                    'eventId':$scope.eventId,
                    'memberId':attendee.id
              }
            }).success(function(data) {})
        }
        $scope.drawRaffle = function() {
            var raffleTargets = $('.attendeeName')
            var raffleText = $('#raffleText')
            var rafflePic = $('#rafflePic')
            var raffleContainer = $('#raffleContainer')

            for(var i = raffleTargets.length - 1; i > 0;) {
                var randomIndex = Math.floor(Math.random() * i);
                i--;
                var temporaryValue = raffleTargets[i];
                raffleTargets[i] = raffleTargets[randomIndex];
                raffleTargets[randomIndex] = temporaryValue;
            }

            //now we want to cycle through a few of these
            var showHide = function(index, left) {
                if(left < 1) {
                    return;
                }

                if(index >= raffleTargets.length) {
                    index = 0;
                }

                raffleContainer.fadeOut(125, function() {
                    var target = raffleTargets.eq(index);
                    rafflePic.attr('src', target.parent().find('img').attr('src'))
                    raffleText.text(target.text())
                    raffleContainer.fadeIn(100, function() {
                        showHide(index+1, left-1);
                    })
                });
            };

            showHide(0, 50);



        }

        $http.get('/raffle',
            { params: { 'eventId':$scope.eventId }}).success(function(data) {
            $scope.attendees = data.attendees;
        })

    });
