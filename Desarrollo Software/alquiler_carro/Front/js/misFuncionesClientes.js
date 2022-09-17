function getClient() {
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Client/all",
        url: "http://localhost:8080/api/Client/all",
        type: "GET",
        datatype: "JSON",
        success: function (clients) {
            drawInfoClients(clients);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    })
}

function drawInfoClients(clients) {

    let myTable = "<table>";
    myTable += "<th>Nombre</th>";
    myTable += "<th>Email</th>";
    myTable += "<th>Edad</th>";
    myTable += "<th>Mensajes</th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "</tr>";
    for (const element of clients) {
        myTable += "<tr>";
        myTable += "<td>" + element.name + "</td>";
        myTable += "<td>" + element.email + "</td>";
        myTable += "<td>" + element.age + "</td>";
        if (typeof element.messages.messageText === 'undefined'){
            myTable += "<td>" + "Ninguno" + "</td>";
        } else {
            myTable += "<td>" + element.messages.messageText + "</td>";
        }
        myTable += '<td><button  onclick="launchDataClient(' + element.idClient + ')">Lanzar</button></td>';
        myTable += "<td> <button onclick=' putClient(" + element.idClient + ")'>Actualizar</button>";
        myTable += "<td> <button onclick='deleteClient(" + element.idClient + ")'>Borrar</button>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#mostrarClientes").html(myTable);
}

function launchDataClient(clientId) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Client/" + clientId,
        url: "http://localhost:8080/api/Client/" + clientId,
        type: 'GET',

        success: function (response) {
            $("#name").val(response.name);
            $("#email").val(response.email);
            $("#age").val(response.age);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });
}

function postClient() {
    if ($("#email").val() === "" || $("#name").val() === "" || $("#age").val() === "") {
        alert("Todos los campos son obligatorios")
    } else{
        let dataClient = {
            email: $("#email").val(),
            name: $("#name").val(),
            age: $("#age").val(),
        };

        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(dataClient),
            //url: "http://10.0.1.5:8080/api/Client/save",
            url: "http://localhost:8080/api/Client/save",
            success: function (response) {
                $("#email").val(" ");
                $("#name").val(" ");
                $("#age").val(" ");
                alert("El cliente "+response.name + " se guardo correctamente");

            },

            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }
        });
    }
}

function putClient(idElemento) {
    if ($("#email").val() === "" || $("#name").val() === "" || $("#Clage").val() === "") {
        alert("Todos los campos son obligatorios")
    } else{
        let clientData = {
            idClient: idElemento,
            email: $("#email").val(),
            name: $("#name").val(),
            age: $("#age").val(),
        };
        $.ajax({
            //url: "http://10.0.1.5:8080/api/Client/update",
            url: "http://localhost:8080/api/Client/update",
            type: "PUT",
            data:  JSON.stringify(clientData),
            contentType: "application/JSON",
            datatype: "JSON",
            success: function (respuesta) {
                $("#email").val(" ");
                $("#name").val(" ");
                $("#age").val(" ");
                alert("Se ha actualizado correctamente el cliente "+respuesta.name)
                getClient()
            },
            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }
        });
}
}

function deleteClient(idElemento) {
    let dataClient = {
        idClient: idElemento
    };
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Client/delete",
        url: "http://localhost:8080/api/Client/delete",
        type: "DELETE",
        data: JSON.stringify(dataClient),
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {
            $("#mostrarClientes").empty();
            alert("El cliente se ha eliminado correctamente")
            $("#email").val(" ");
            $("#name").val(" ");
            $("#age").val(" ");
            getClient();
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });

}
