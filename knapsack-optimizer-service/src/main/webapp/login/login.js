angular.module( 'sample.login', [
  'ui.router',
  'angular-storage'
])
.config(function($stateProvider) {
  $stateProvider.state('login', {
    url: '/login',
    controller: 'LoginCtrl',
    templateUrl: 'login/login.html'
  });
})
.controller( 'LoginCtrl', function LoginController( $scope, $http, store, $state) {

  $scope.user = {};

  $scope.login = function() {
    $http({
      url: '/login',
      method: 'POST',
      data: $scope.user
    }).then(function(response) {
      store.set('jwt', response.headers('Authorization'));
      $state.go('home');
    }, function(error) {
      alert(error.data);
    });
  }

});
