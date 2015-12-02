'use strict';
app.controller('DashboardCtrl', function DashboardCtrl($scope, $state, snapRemote) {

    $scope.isActive = false;

    $scope.rotate = function () {
        $scope.isActive = !$scope.isActive;
    };

    snapRemote.getSnapper().then(function(snapper) {
        snapper.on('open', function() {
            $scope.rotate();
        });

        snapper.on('close', function() {
            $scope.rotate();
        });
    });

    $scope.goTo = function (state) {
        snapRemote.close();
        $state.go(state);
    }

});