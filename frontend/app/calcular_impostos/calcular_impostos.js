'use strict';

angular.module('contabilizeiApp.calcular_impostos', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/calcular_impostos', {
            templateUrl: 'calcular_impostos/calcular_impostos.html',
            controller: 'CalcularImpostosCtrl'
        });
    }])

    .controller("CalcularImpostosCtrl", ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
        var dataObj = {
            "maxPorPagina" : 1000
        };
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