'use strict';
app.controller('HomeCtrl', function HomeCtrl($scope, $state, NgTableParams, accountFactory) {



    $scope.homePageInit = function() {
        $scope.trxData = {
            trxSet : []
        };
        $scope.initWeekChart();
        $scope.initMainWidget();
        accountFactory.weeklyStatus()
            .success(function(data) {
                $scope.trxData = data;
                $scope.data = [
                    [
                        $scope.trxData.day0total,
                        $scope.trxData.day1total,
                        $scope.trxData.day2total,
                        $scope.trxData.day3total,
                        $scope.trxData.day4total,
                        $scope.trxData.day5total,
                        $scope.trxData.day6total],
                    [
                        $scope.trxData.prevDay0total,
                        $scope.trxData.prevDay1total,
                        $scope.trxData.prevDay2total,
                        $scope.trxData.prevDay3total,
                        $scope.trxData.prevDay4total,
                        $scope.trxData.prevDay5total,
                        $scope.trxData.prevDay6total]
                ];
                $scope.current =        $scope.trxData.currentWeekAmount * 100;
                $scope.max =            $scope.trxData.weeklyTarget * 100;
                $scope.showMax =        $scope.trxData.weeklyTarget;
                $scope.showCurrent =    $scope.trxData.currentWeekAmount;

            })
            .error(function (error) {
                NibbleUtils.errorCallback($scope, error)
            });
    };


    $scope.initWeekChart = function() {
        $scope.labels = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        $scope.series = ['Current week', 'Previous week'];
        $scope.data = [
            [ 0, 0, 0, 0, 0, 0, 0],
            [ 0, 0, 0, 0, 0, 0, 0]
        ];
        $scope.colours = ['#008000', '#FDB45C'];
    };

    $scope.initMainWidget = function() {
        $scope.current =        0;
        $scope.max =            999;
        $scope.offset =         0;
        $scope.timerCurrent =   0;
        $scope.uploadCurrent =  0;
        $scope.stroke =         20;
        $scope.radius =         125;
        $scope.isSemi =         false;
        $scope.rounded =        true;
        $scope.responsive =     false;
        $scope.clockwise =      true;
        $scope.currentColor =   '#008000';
        $scope.bgColor =        '#fa8e09';
        $scope.duration =       3000;
        $scope.currentAnimation = 'easeOutCubic';
        $scope.gradient =       false;

        $scope.showMax =$scope.max / 100;
        $scope.showCurrent = $scope.current / 100;
    };


    $scope.getStyle = function(){
        var transform = ($scope.isSemi ? '' : 'translateY(-50%) ') + 'translateX(-50%)';
        return {
            'top': $scope.isSemi ? 'auto' : '50%',
            'bottom': $scope.isSemi ? '5%' : 'auto',
            'left': '50%',
            'transform': transform,
            '-moz-transform': transform,
            '-webkit-transform': transform,
            'font-size': $scope.radius/4 + 'px'
        };
    };

    $scope.getColor = function(){
        return $scope.gradient ? 'url(#gradient)' : $scope.currentColor;
    };

    $scope.initTrxTable = function() {
        $scope.tableSettings = new NgTableParams(
            {
                count : 5},
            {
                counts:[],
                paginationMaxBlocks: 5,
                paginationMinBlocks: 2,
                dataset : $scope.trxData.trxSet

            }
        )
    };


    $scope.getTrxList = function(param) {
        console.log(param);
    };

});