var fechaActual = Date();
let nombreDiaActual = fechaActual[0] + fechaActual[1] + fechaActual[2];
console.log(nombreDiaActual);
let nombreDiaSemana;

switch (nombreDiaActual) {
    case 'Mon':
        nombreDiaSemana = 'LUNES';
        break;
    case 'Tue':
        nombreDiaSemana = 'MARTES';
        break;
    case 'Wed':
        nombreDiaSemana = 'MIERCOLES';
        break;
    case 'Thu':
        nombreDiaSemana = 'JUEVES';
        break;
    case 'Fri':
        nombreDiaSemana = 'VIERNES';
        break;
    case 'Sat':
        nombreDiaSemana = 'SABADO';
        break;
    case 'Sun':
        nombreDiaSemana = 'DOMINGO';
        break;
}
console.log(nombreDiaSemana);
let nombreMes = fechaActual[4] + fechaActual[5] + fechaActual[6];
let mes = consultarMes(nombreMes);
console.log(mes);
let dia = fechaActual[8] + fechaActual[9];
let anio = fechaActual[11] + fechaActual[12] + fechaActual[13] + fechaActual[14];
let fecha = nombreDiaSemana + ', ' + dia + ' DE ' + mes + ' DE ' + anio;
setTimeout(function () {
    document.getElementById('fecha').innerHTML = fecha;
});

function consultarMes(mes) {
    const mesDict = {
        Jan: 'ENERO',
        Feb: 'FEBRERO',
        Mar: 'MARZO',
        Apr: 'ABRIL',
        May: 'MAYO',
        Jun: 'JUNIO',
        Jul: 'JULIO',
        Aug: 'AGOSTO',
        Sep: 'SEPTIEMBRE',
        Oct: 'OCTUBRE',
        Nov: 'NOVIEMBRE',
        Dec: 'DICIEMBRE'
    }
    return mesDict[mes];
}