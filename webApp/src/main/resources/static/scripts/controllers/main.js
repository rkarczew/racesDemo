
angular.module('racesApp')
  .controller('MainCtrl', function ($scope, $http) {     });

angular.module('racesApp')
  .controller('MainCtrl', function ($scope, $http) {   
      $http({
          method: 'GET',
          url: 'http://localhost:8282/races'
      }).then(function(response) {
          $scope.races = response.data;
      }, function(response) {
          console.error('Error requesting races');
      });
  });