/////////Tabla Cliente////////////////////////////

function traerInformacionClientes() {
    console.log("se esta ejecutando")
    $.ajax({
        url: "http://168.138.142.130:8080/api/Client/all",
        //url: "http://localhost:8080/api/Client/all",
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
    myTable += "<td>Nombre</td>";
    myTable += "<td>Email</td>";
    myTable += "<td>Password</td>";
    myTable += "<td>age</td>";
    "</tr>";
    for (i = 0; i < respuesta.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + respuesta[i].name + "</td>";
        myTable += "<td>" + respuesta[i].email + "</td>";
        myTable += "<td>" + respuesta[i].password + "</td>";
        myTable += "<td>" + respuesta[i].age + "</td>";
        myTable += "<td> <button onclick=' actualizarInformacionCliente(" + respuesta[i].idClient + ")'>Actualizar</button>";
        myTable += '<td><button  onclick="cargarDatosClientes(' + respuesta[i].idClient + ')">Lanzar</button></td>';
        myTable += "<td> <button onclick='borrarCliente(" + respuesta[i].idClient + ")'>Borrar</button>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#mostrarClientes").html(myTable);
}

function cargarDatosClientes(id) {
    $.ajax({
        dataType: 'json',
        url: "http://168.138.142.130:8080/api/Client/" + id,
        //url: "http://localhost:8080/api/Client/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#Clname").val(item.name);
            $("#Clemail").val(item.email);
            $("#Clpassword").val(item.password);
            $("#Clage").val(item.age);
        },

        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
}

function guardarInformacionCliente() {
    let var2 = {

        email: $("#Clemail").val(),
        password: $("#Clpassword").val(),
        name: $("#Clname").val(),
        age: $("#Clage").val(),

    };

    console.log(var2);
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'JSON',
        data: JSON.stringify(var2),
        url: "http://168.138.142.130:8080/api/Client/save",
        //url: "http://localhost:8080/api/Client/save",


        success: function (response) {
            console.log(response);
            console.log("Se guardo correctamente");
            alert("Se guardo correctamente");
            window.location.reload()

        },

        error: function (jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("No se guardo correctamente");


        }
    });

}

function actualizarInformacionCliente(idElemento) {
    let myData = {
        idClient: idElemento,
        email: $("#Clemail").val(),
        password: $("#Clpassword").val(),
        name: $("#Clname").val(),
        age: $("#Clage").val(),


    };
    console.log(myData);
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http://168.138.142.130:8080/api/Client/update",
        //url: "http://localhost:8080/api/Client/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            $("#idClient").val("");
            $("#Clemail").val("");
            $("#Clpassword").val("");
            $("#Clname").val("");
            $("#Clage").val("");
            alert("se ha Actualizado correctamente Cliente")
            traerInformacionClientes()
        }
    });

}

function borrarCliente(idElemento) {
    let myData = {
        idClient: idElemento
    };
    let dataToSend = JSON.stringify(myData);
    console.log(dataToSend);
    $.ajax({
        url: "http://168.138.142.130:8080/api/Client/" + idElemento,
        //url: "http://localhost:8080/api/Client/" + idElemento,
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            traerInformacionClientes();
            alert("Se ha Eliminado.")
        }
    });

}