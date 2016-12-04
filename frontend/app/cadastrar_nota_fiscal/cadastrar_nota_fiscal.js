'use strict';

angular.module('contabilizeiApp.cadastrar_nota_fiscal', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cadastrar_nota_fiscal', {
            templateUrl: 'cadastrar_nota_fiscal/cadastrar_nota_fiscal.html',
            controller: 'CadastrarNotaFiscalCtrl'
        });
    }])

    .controller("CadastrarNotaFiscalCtrl", ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
        var dataObj = {
            "maxPorPagina": 1000
        };
        var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/clientes', dataObj);

        res.success(function (data, status, headers, config) {
            $scope.clientes = data.result.clientes;
        });
        res.error(function (data, status, headers, config) {
            console.error('ERROR');
            console.error(data);
            alert("failure message: " + JSON.stringify({data: data}));
        });
    }]);