var app = angular.module('nametags', ['ngRoute']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
    when('/events', {
        controller: 'events', templateUrl: 'partials/list.html'
    }).
    when('/events/:eventId', {
        controller: 'members', templateUrl: 'partials/event.html'
    }).
    otherwise({
        redirectTo: '/events'
    });
}]);

app.controller('events', function ($scope, $http) {
    $scope.events = [];
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
