$(document).ready(function() {
	$('#example').DataTable( {
        "ajax": "/js/customer/object.txt",
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "office" },
            { "data": "extn" },
            { "data": "start_date" },
            { "data": "salary" }
        ]
    } );
    var table = $('#example').DataTable();
//    $('#example tbody').on( 'click', 'tr', function () {
//    	var rows = table.row( '.selected' ).index();
//        var data = table.rows(rows).data();
//        var idx = this.rowIndex - 1 ;
//    	alert(data[idx][0]);
//    } );
    $('#example tbody').on( 'click', 'td', function () {
        var visIdx = $(this).index();
        var dataIdx = table.column.index( 'fromVisible', visIdx );
     
        alert( 'Column data index: '+dataIdx+', and visible index: '+visIdx );
    } );
} );