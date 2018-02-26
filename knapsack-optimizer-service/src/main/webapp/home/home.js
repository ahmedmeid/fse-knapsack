angular.module( 'sample.home', [
  'ui.router',
  'angular-storage',
  'angular-jwt'
])
.config(function($stateProvider) {
  $stateProvider.state('home', {
    url: '/',
    controller: 'HomeCtrl',
    templateUrl: 'home/home.html',
    data: {
      requiresLogin: true
    }
  });
})
.controller( 'HomeCtrl', function HomeController( $scope, $http, store, jwtHelper) {

  $scope.jwt = store.get('jwt');
  $scope.decodedJwt = $scope.jwt && jwtHelper.decodeToken($scope.jwt);

  $scope.getTaskDetails = function() {
    callApi('Secured', '/knapsack/tasks/'+$scope.taskId);
  }

  function callApi(type, url) {
    $scope.response = null;
    $scope.api = type;
    $http({
      url: url,
      method: 'GET'
    }).then(function(task) {
      $scope.response = task.data;
      
      var d = new Date(0);            
      d.setUTCSeconds(task.data.timestamps.submitted);    
      $scope.response.submitted = d;
      var d = new Date(0); 
      d.setUTCSeconds(task.data.timestamps.started);
      $scope.response.started = d;
      var d = new Date(0); 
      d.setUTCSeconds(task.data.timestamps.completed);
      $scope.response.completed = d;  
    }, function(error) {
      $scope.response = error.data;
    });
  }

});
