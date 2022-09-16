function traerReporteDate() {

    var fechaInicio = $("#rStartDate").val();
    var fechaCierre = $("#rDevolutionDate").val();
    console.log(fechaInicio);
    console.log(fechaCierre);

    $.ajax({
        //url: "http://10.0.1.5:8080/api/Reservation/report-dates/" + fechaInicio + "/" + fechaCierre,
        url:"http://localhost:8080/api/Reservation/report-dates/"+fechaInicio+"/"+fechaCierre,
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarRespuestaDate(respuesta);
        }
    });
}

function  formatDate (date) {
    let day = date.getDate();
    let month = (date.getMonth() + 1);
    let year = date.getFullYear();
    let formatted_date;
    let diaString;
    let monthString;
    if (day <10){
        diaString = "0" + day.toString()
    }else{
        diaString = day.toString()
    }
    if (month <10){
        monthString = "0" + month.toString()
    }else{
        monthString = month.toString()
    }
        formatted_date = diaString +"-" + monthString +"-" +year;
    return formatted_date;
}
function pintarRespuestaDate(respuesta) {

    let myTable = "<table>";
    myTable += "<tr>";
    myTable += "<th>Fecha Inicio</th>";
    myTable += "<th>Fecha Devolucion</th>";
    myTable += "<th>Estado</th>";
    myTable += "<th>Cliente</th>";
    myTable += "<th>Vehiculo</th>";
    myTable += "<th>Mensaje</th>";
    myTable += "<th>Califcación</th>";
    "</tr>";

    for (i = 0; i < respuesta.length; i++) {
        
        myTable += "<tr>";
        myTable += "<td>" + formatDate(new Date(respuesta[i].startDate)) + "</td>";
        myTable += "<td>" + formatDate(new Date(respuesta[i].devolutionDate)) + "</td>";
        myTable += "<td>" + respuesta[i].status + "</td>";
        myTable += "<td>" + respuesta[i].client.name + "</td>";
        myTable += "<td>" + respuesta[i].vehicle.name + "</td>";
        if(respuesta[i].messages.length != 0){
            myTable += "<td>" + respuesta[i].messages[0].messageText + "</td>";
            myTable += "<td>" + respuesta[i].messages[0].score + "</td>";
        }else{
            myTable += "<td>" + "No hay mensajes" + "</td>";
            myTable += "<td>" + "No hay calificación" + "</td>";
        }
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoDate").html(myTable);
}

function traerReporteStatus() {
    console.log("test");
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Reservation/report-status",
        url:"http://localhost:8080/api/Reservation/report-status",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarRespuesta(respuesta);
        }
    });
}

function pintarRespuesta(respuesta) {

    let myTable = "<table>";
    myTable += "<tr>";
    myTable += "<th>completadas</th>";
    myTable += "<td>" + respuesta.completed + "</td>";
    myTable += "<th>canceladas</th>";
    myTable += "<td>" + respuesta.cancelled + "</td>";
    myTable += "</tr>";
    myTable += "</table>";
    $("#resultadoStatus").html(myTable);
}


function traerReporteClientes() {
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Reservation/report-clients",
        url:"http://localhost:8080/api/Reservation/report-clients",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarRespuestaClientes(respuesta);
        }
    });
}

function pintarRespuestaClientes(respuesta) {

    let myTable = "<table>";
    myTable += "<th>Cantdad de reservas</th>";
    myTable += "<th>Cliente</th>";
    myTable += "<tr>";

    for (i = 0; i < respuesta.length; i++) {
        
        myTable += "<td>" + respuesta[i].total + "</td>";
        myTable += "<td>" + respuesta[i].client.name + "</td>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoClientes").html(myTable);
}