function traerInformacionClientes() {
    console.log("se esta ejecutando")
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Client/all",
        url: "http://localhost:8080/api/Client/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarCliente(respuesta);
        }
    })
}

function pintarCliente(respuesta) {

    let myTable = "<table>";
    myTable += "<th>Nombre</th>";
    myTable += "<th>Email</th>";
    myTable += "<th>Edad</th>";
    myTable += "<th>Mensajes</th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    "</tr>";
    for (i = 0; i < respuesta.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + respuesta[i].name + "</td>";
        myTable += "<td>" + respuesta[i].email + "</td>";
        myTable += "<td>" + respuesta[i].age + "</td>";
        if (typeof respuesta[i].messages.messageText === 'undefined'){
            myTable += "<td>" + "Ninguno" + "</td>";
        } else {
            myTable += "<td>" + respuesta[i].messages.messageText + "</td>";
        }
        myTable += '<td><button  onclick="cargarDatosClientes(' + respuesta[i].idClient + ')">Lanzar</button></td>';
        myTable += "<td> <button onclick=' actualizarInformacionCliente(" + respuesta[i].idClient + ")'>Actualizar</button>";
        myTable += "<td> <button onclick='borrarCliente(" + respuesta[i].idClient + ")'>Borrar</button>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#mostrarClientes").html(myTable);
}

function cargarDatosClientes(id) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Client/" + id,
        url: "http://localhost:8080/api/Client/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#name").val(item.name);
            $("#email").val(item.email);
            $("#age").val(item.age);
        }
    });
}

function agregarCliente() {
    if ($("#email").val()=="" || $("#name").val() == "" || $("#age").val()=="") {
        alert("Todos los campos son obligatorios")
    } else{
        let datos = {
            email: $("#email").val(),
            name: $("#name").val(),
            age: $("#age").val(),
        };

        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(datos),
            //url: "http://10.0.1.5:8080/api/Client/save",
            url: "http://localhost:8080/api/Client/save",
            success: function (response) {
                console.log(response);
                alert("Se guardo correctamente");
                window.location.reload()
            },

            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se guardo correctamente");
            }
        });
    }
}

function actualizarInformacionCliente(idElemento) {
    if ($("#email").val() == "" || $("#name").val() == "" || $("#Clage").val() == "") {
        alert("Todos los campos son obligatorios")
    } else{
        let myData = {
            idClient: idElemento,
            email: $("#email").val(),
            name: $("#name").val(),
            age: $("#age").val(),
        };
        console.log(myData);
        let dataToSend = JSON.stringify(myData);
        $.ajax({
            //url: "http://10.0.1.5:8080/api/Client/update",
            url: "http://localhost:8080/api/Client/update",
            type: "PUT",
            data: dataToSend,
            contentType: "application/JSON",
            datatype: "JSON",
            success: function (respuesta) {
                $("#email").val(" "),
                    $("#name").val(" "),
                    $("#age").val(" "),
                    alert("se ha Actualizado correctamente ")
                traerInformacionClientes()
            },
            error: function (jqXHR, textStatus, errorThrown) {
                window.location.reload()
                alert("No se actualizo correctamente");
            }
        });
}
}

function borrarCliente(idElemento) {
    let myData = {
        idClient: idElemento
    };
    let dataToSend = JSON.stringify(myData);
    console.log(dataToSend);
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Client/delete",
        url: "http://localhost:8080/api/Client/delete",
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#mostrarClientes").empty();
            alert("Se ha eliminado correctamente")
            $("#email").val(" "),
            $("#name").val(" "),
            $("#age").val(" "),
            traerInformacionClientes();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("No se borro correctamente");
        }
    });

}