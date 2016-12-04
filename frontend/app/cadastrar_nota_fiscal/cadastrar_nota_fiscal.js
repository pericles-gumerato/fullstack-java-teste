'use strict';

angular.module('contabilizeiApp.cadastrar_nota_fiscal', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cadastrar_nota_fiscal', {
            templateUrl: 'cadastrar_nota_fiscal/cadastrar_nota_fiscal.html',
            controller: 'CadastrarNotaFiscalCtrl'
        });
    }])

    .controller('CadastrarNotaFiscalCtrl', ['$scope', '$http', '$sce', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, $sce, backendAddress) {
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
            $scope.mensagemErro = $sce.trustAsHtml('Erro ao recuperar clientes!');
        });

        $scope.cadastrarNotaFiscal = function (clienteId) {
            // Clean messages
            $scope.mensagemStatus = $sce.trustAsHtml('');

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
                $scope.mensagemStatus = $sce.trustAsHtml('Nota Fiscal cadastrada');
            });
            res.error(function (data, status, headers, config) {
                $scope.mensagemErro = $sce.trustAsHtml('Erro ao realizar a ação.');
            });
        };
    }]);