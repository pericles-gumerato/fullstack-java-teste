'use strict';

angular.module('contabilizeiApp.cadastrar_nota_fiscal', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cadastrar_nota_fiscal', {
            templateUrl: 'cadastrar_nota_fiscal/cadastrar_nota_fiscal.html',
            controller: 'CadastrarNotaFiscalCtrl'
        });
    }])

    .controller('CadastrarNotaFiscalCtrl', ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
        $scope.anexos = {};
        var dataObj = {
            'maxPorPagina': 1000
        };
        $scope.notas = [];
        var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/clientes', dataObj);

        res.success(function (data, status, headers, config) {
            $scope.clientes = data.result.clientes;
        });
        res.error(function (data, status, headers, config) {
            console.error('ERROR');
            console.error(data);
            alert('failure message: ' + JSON.stringify({data: data}));
        });

        $scope.cadastrarNotaFiscal = function (clienteId) {

            var formData = $scope.cadastroNotaForm;

            var dataObj = {
                'clienteId': clienteId,
                'numero': formData.numero.$modelValue,
                'dataEmissao': formData.dataEmissao.$modelValue,
                'descricao': formData.descricao.$modelValue,
                'valorCentavos': formData.valor.$modelValue * 100,
                'anexo': formData.anexo.$modelValue
            };
            var res = $http.put(backendAddress.url + ':' + backendAddress.port + '/cadastro/nota_fiscal', dataObj);

            res.success(function (data, status, headers, config) {
                $scope.mensagemStatus = 'Nota Fiscal cadastrada';
            });
            res.error(function (data, status, headers, config) {
                $scope.mensagemStatus = 'Erro ao realizar a ação.';
            });
        };
    }]);