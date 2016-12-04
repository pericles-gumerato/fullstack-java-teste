'use strict';

angular.module('contabilizeiApp.cadastrar_cliente', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cadastrar_cliente', {
            templateUrl: 'cadastrar_cliente/cadastrar_cliente.html',
            controller: 'CadastrarClienteCtrl'
        });
    }])

    .controller("CadastrarClienteCtrl", ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
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