let libros = [];
let libros2 = [];
let libros3 = [];
let libros4 = [];
let fotoB64 = null;
let inputFileArmazon = null;

export function inicializar() {
    inputFileArmazon = document.getElementById("inputFileImagenArmazon");
    inputFileArmazon.onchange = function (evt) {
        cargarFotografia(inputFileArmazon);
    };
}


export function loadTable2(data) {
    let cuerpo = "";
    libros = data;

    libros.forEach(function (Libro) {
        let registro =
                '<tr>' +
                '<td>' + Libro.titulo_libro + '</td>' +
                '<td>' + Libro.autor_libro + '</td>' +
                '<td>' + Libro.tema_libro + '</td>' +
                '<td><a href="#" onclick="moduloBuscarL.mostrarDetalle(' + Libro.id + ')">Seleccionar</a></td>' + '</tr>';
        cuerpo += registro;
    });

    document.getElementById("tblLibro").innerHTML = cuerpo;
}

export function loadTable(data, data2) {
    let cuerpo = "";
    libros = data;
    libros2 = data2;

    libros.forEach(function (Libro) {
        let registro =
                '<tr>' +
                '<td>' + Libro.titulo_libro + '</td>' +
                '<td>' + Libro.autor_libro + '</td>' +
                '<td>' + Libro.tema_libro + '</td>' +
                '<td><a href="#" onclick="moduloBuscarL.mostrarDetalle(' + Libro.id + ')">Seleccionar</a></td>' + '</tr>';
        cuerpo += registro;
    });

    libros2.forEach(function (Libro) {
        let registro =
                '<tr>' +
                '<td>' + Libro.titulo_libro + '</td>' +
                '<td>' + Libro.autor_libro + '</td>' +
                '<td>' + Libro.tema_libro + '</td>' +
                '<td><a href="#" onclick="moduloBuscarL.mostrarDetalle2(' + Libro.id + ')">Seleccionar</a></td>' + '</tr>';
        cuerpo += registro;
    });

    document.getElementById("tblLibro").innerHTML = cuerpo;
}

export function buscar() {

    let filtro = document.getElementById("txtBusquedaLibro").value;

    let datos = {
        nombre_libro: filtro
    };

    let params = new URLSearchParams(datos);

    fetch("api/busc/LVMPublic", {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        body: params
    })
            .then(response => response.json())
            .then(data => {
                if (data.exception != null) {
                    swal.fire('', 'Error interno del servidor. Intente nuevamente más tarde.', 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('', data.error, 'warning');
                    return;
                }
                if (data.errosec != null) {
                    Swal.fire('', data, errorsec, 'error');
                    window.location.replace('index.html');
                    return;
                }
                loadTable2(data);
            });

}
export function buscar2(dataA) {

    let filtro = document.getElementById("txtBusquedaLibro").value;
    let url;
    url = "http://192.168.137.143:8080/Biblioteca/api/alumnoLibro/buscar?filtro=" + filtro;
//    url = "api/alumnoLibro/buscar?filtro=" + filtro;
    fetch(url)
            .then(response => {
                return response.json();
            })
            .then(function (data)
            {
                if (data.exception != null)
                {
                    swal.fire('', 'Error interno del servidor. Intente nuevamente más tarde.', 'error');
                    return;
                }
                if (data.error != null)
                {
                    Swal.fire('', data.error, 'warning');
                    return;
                }
                loadTable(dataA, data);
                //buscar3(dataA, data);
            });

}


export function mostrarDetalle(id) {
    libros.forEach(function (libro) {
        if (id.toString() === libro.id.toString()) {
            console.log(libro.libroPDF);
            document.getElementById("txtCodigoImagen").value = libro.libroPDF;
            document.getElementById("txtIdLibro").value = libro.id;


            //document.getElementById("imgFoto").src = "data:pdf/" +
            //     getImageFormat(fotoB64) + ";base64," + libro.id_libro;
            mostrarPDFDesdeBase64();
            refrescarTabla();
        }
    });

    //document.getElementById("btnDelete").classList.remove("disabled");
}
export function mostrarDetalle2(id) {
    libros2.forEach(function (libro) {
        if (id.toString() === libro.id.toString()) {
            document.getElementById("txtCodigoImagen").value = libro.libroPDF;
            document.getElementById("txtIdLibro").value = libro.id;


            //document.getElementById("imgFoto").src = "data:pdf/" +
            //     getImageFormat(fotoB64) + ";base64," + libro.id_libro;
            mostrarPDFDesdeBase64();
            refrescarTabla();



        }
    });

}


export function clean() {

    document.getElementById("txtIdLibro").value = "";
    document.getElementById("txtCodigoImagen").value = "";
    document.getElementById("imgFoto").setAttribute("src", "");

}


export function showInputDialog() {
    document.getElementById("inputFileImagenArmazon").click();
}

function cargarFotografia(objetoInputFile) {
    //Revisamos que el usuario haya seleccionado un archivo
    if (objetoInputFile.files && objetoInputFile.files[0]) {
        let reader = new FileReader();

        //Agregamos un oyente al lector del archivo para que,
        //en cuanto el usuario cargue una imagen, esta se lea
        //y se convierta de forma automatica en una cadena de Base64
        reader.onload = function (e) {
            fotoB64 = e.target.result;
            document.getElementById("imgFoto").src = fotoB64;

            //fotoB64 = fotoB64.substring(fotoB64.indexOf(",") + 1, fotoB64.length);
            document.getElementById("txtCodigoImagen").value =
                    fotoB64.substring(fotoB64.indexOf(",") + 1, fotoB64.length);
        };

        //Leemos el archivo que seleccionó el usuario y lo
        //convertimos en una cadena con la Base64
        reader.readAsDataURL(objetoInputFile.files[0]);
    }
}


export function getImageFormat(strb64) {
    let fc = strb64 != null && strb64.length > 0 ? strb64.subtr(0, 1) : "";

    switch (fc) {
        case "/" :
            return "jpeg";
        case "i" :
            return "png";
        case "Q" :
            return "bmp";
        case "S" :
            return "tiff";
        case "J" :
            return "pdf";
        case "U" :
            return "webp";
        case "R" :
            return "gif";
    }
}

function mostrarPDFDesdeBase64() {

    let b64 = document.getElementById("txtCodigoImagen").value;

    var bin = atob(b64);
    console.log('File Size:', Math.round(bin.length / 1024), 'KB');
    console.log('PDF Version:', bin.match(/^.PDF-([0-9.]+)/)[1]);

    //document.getElementById("imgFoto").src = 'data:application/pdf;base64,' + b64;

    var obj = document.createElement('object');
    obj.style.width = '86%';
    obj.style.height = '842pt';
    obj.style.float = 'right';
    obj.type = 'application/pdf';
    obj.data = 'data:application/pdf;base64,' + b64;
    document.body.appendChild(obj);

    document.getElementById("tabla").classList.add("d-none");

    document.getElementById("txtBusquedaLibro").addEventListener("click", function () {
        document.getElementById("tabla").classList.remove("d-none");
        document.body.removeChild(obj);
    });
}