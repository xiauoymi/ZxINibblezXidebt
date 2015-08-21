;
nibbleaccts = {
    weeklyChart: null,
    name: 'nibbleaccts',

    init: function () {
        $('#home-content').load('views/home/' + nibbleaccts.name + '.html', function () {
            nibbleaccts.initAccounts();
        });
    },

    initAccounts: function () {
        var request = {};
        request.type = 'GET';
        request.url = nibblemain.getServicesUrl() + '/rest/useraccounts';
        request.done = function (data) {
            if (data) {
                var accounts = data.items;
                var panelGroupDiv = $('#accs-accordion');
                panelGroupDiv.html('');
                for (var i = 0; i < accounts.length; i++) {
                    var acc = accounts[i];
                    var panelMain = $("<div>", {class: "panel panel-default"});
                    var panelDiv = $("<div>", {
                        class: "panel-heading bg-image",
                        style: "background-image: url(" + nibblemain.getServicesUrl() + "/rest/logo/" + acc.institutionName + ")"
                    });
                    var panelTitle = $("<h4>", {class: "panel-title"});
                    panelTitle.append('<a data-toggle="collapse" data-parent="#accs-accordion" href="#collapse' + i + '">' +
                    acc.institutionName + ' : $' + acc.balance +
                    '</a>');
                    panelDiv.append(panelTitle);
                    var collapse = $("<div>", {id: "collapse" + i, class: "panel-collapse collapse"});
                    var panelBody = $("<div>", {class: "panel-body"});
                    collapse.append(panelBody);
                    panelMain.append(panelDiv);
                    panelMain.append(collapse);
                    panelGroupDiv.append(panelMain);

                }

                $('#accs-accordion').on('show.bs.collapse', function(e){
                    var depId  = $(e.target).attr('id').replace('collapse','');
                    var selectedAccount = accounts[depId];
                    var requestTx = {};
                    requestTx.type = 'GET';
                    requestTx.url = nibblemain.getServicesUrl() + '/rest/acctrxs/' + selectedAccount.accountId;
                    requestTx.done = function (dataTx) {
                        if (dataTx) {
                            var trxs = dataTx.items;
                            console.log(dataTx);
                            if (trxs) {
                                var contentCollapsePanel = $('#collapse' + depId);
                                contentCollapsePanel.html('');
                                var panelBody = $("<div>", {class: "panel-body"});
                                var panelBodyTable = $("<table>", {id: "accTrxTable" + selectedAccount.accountId,
                                    class: "table table-striped table-responsive",
                                    style: "width:100%;"});
                                var tableHeader = $("<thead>", {});
                                var tableTr = $("<tr>", {});
                                tableTr.append('<th>From</th>');
                                tableTr.append('<th>Amount</th>');
                                tableTr.append('<th>Rounded to</th>');
                                tableTr.append('<th>Spent at</th>');
                                tableHeader.append(tableTr);
                                panelBodyTable.append(tableHeader);
                                panelBodyTable.append('<tbody></tbody>');
                                panelBody.append(panelBodyTable);
                                contentCollapsePanel.append(panelBody);

                                var trxtable = $('#accTrxTable' + selectedAccount.accountId).DataTable({
                                    responsive: true,
                                    bLengthChange : false,
                                    iDisplayLength : 5
                                });
                                for(var k=0; k<trxs.length; k++){
                                    var place = '';
                                    if(trxs[k].city!=null && trxs[k].state!=null) place = trxs[k].city+', '+trxs[k].state;
                                    else if(trxs[k].city == null && trxs[k].state!=null) place = trxs[k].state;
                                    else if(trxs[k].city != null && trxs[k].state==null) place = trxs[k].city;
                                    else place = 'Unknown';
                                    trxtable.row.add([

                                        trxs[k].institutionName+' ..'+trxs[k].accountNumber,
                                        '$ '+trxs[k].trxAmount,
                                        '$ '+trxs[k].roundupAmount,
                                        place
                                    ]).draw();
                                }
                            }
                        }
                    };
                    nibblemain.jsonasync(requestTx);

                });
            }
        };
        nibblemain.jsonasync(request);
    }
};