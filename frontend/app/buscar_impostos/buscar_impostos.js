'use strict';

angular.module('contabilizeiApp.buscar_impostos', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/buscar_impostos', {
            templateUrl: 'buscar_impostos/buscar_impostos.html',
            controller: 'BuscarImpostosCtrl'
        });
    }])

    .controller("BuscarImpostosCtrl", ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
        var dataObj = {};
        var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/clientes', dataObj);

        res.success(function (data, status, headers, config) {
            $scope.clientes=data.result.clientes;
        });
        res.error(function (data, status, headers, config) {
            console.error('ERROR');
            console.error(data);
            alert("failure message: " + JSON.stringify({data: data}));
        });
    }]);