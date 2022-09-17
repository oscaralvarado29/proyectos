function getReportDate() {

    let fechaInicio = $("#rStartDate").val();
    let fechaCierre = $("#rDevolutionDate").val();

    $.ajax({
        //url: "http://10.0.1.5:8080/api/Reservation/report-dates/" + fechaInicio + "/" + fechaCierre,
        url:"http://localhost:8080/api/Reservation/report-dates/"+fechaInicio+"/"+fechaCierre,
        type: "GET",
        datatype: "JSON",
        success: function (reportDate) {
            drawReportDates(reportDate);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });
}

function drawReportDates(reportDate) {

    let myTable = "<table>";
    myTable += "<tr>";
    myTable += "<th>Fecha Inicio</th>";
    myTable += "<th>Fecha Devolucion</th>";
    myTable += "<th>Estado</th>";
    myTable += "<th>Cliente</th>";
    myTable += "<th>Vehiculo</th>";
    myTable += "<th>Mensaje</th>";
    myTable += "<th>Califcación</th>";
    myTable += "</tr>";

    for (const element of reportDate) {
        
        myTable += "<tr>";
        myTable += "<td>" + formatDate(new Date(element.startDate),1) + "</td>";
        myTable += "<td>" + formatDate(new Date(element.devolutionDate),1) + "</td>";
        myTable += "<td>" + element.status + "</td>";
        myTable += "<td>" + element.client.name + "</td>";
        myTable += "<td>" + element.vehicle.name + "</td>";
        if(element.messages.length !== 0){
            myTable += "<td>" + element.messages[0].messageText + "</td>";
            myTable += "<td>" + element.messages[0].score + "</td>";
        }else{
            myTable += "<td>" + "No hay mensajes" + "</td>";
            myTable += "<td>" + "No hay calificación" + "</td>";
        }
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoDate").html(myTable);
}

function getReportStatus() {
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Reservation/report-status",
        url:"http://localhost:8080/api/Reservation/report-status",
        type: "GET",
        datatype: "JSON",
        success: function (reportStatus) {
            drawReportStatus(reportStatus);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });
}

function drawReportStatus(reportStatus) {


    let myTable = "<table>";
    myTable += "<tr>";
    myTable += "<th>completadas</th>";
    myTable += "<td>" + reportStatus.completed + "</td>";
    myTable += "<th>canceladas</th>";
    myTable += "<td>" + reportStatus.cancelled + "</td>";
    myTable += "</tr>";
    myTable += "</table>";
    $("#resultadoStatus").html(myTable);
}


function getReportClients() {
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Reservation/report-clients",
        url:"http://localhost:8080/api/Reservation/report-clients",
        type: "GET",
        datatype: "JSON",
        success: function (reportClients) {
            drawReportClients(reportClients);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });
}

function drawReportClients(reportClients) {

    let myTable = "<table>";
    myTable += "<th>Cantdad de reservas</th>";
    myTable += "<th>Cliente</th>";
    myTable += "<tr>";

    for (const element of reportClients) {
        
        myTable += "<td>" + element.total + "</td>";
        myTable += "<td>" + element.client.name + "</td>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoClientes").html(myTable);
}