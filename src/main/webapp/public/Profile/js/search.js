$(document).ready(function() {
    $('#button-id').click(function() {
      foo($('#formValueId').val());
    });
});

function displayDataCard2() {

  var json_obj  = {
      "name":"John",
      "age":30,
      "cars": [
        { "name":"Maruti", "models":[  "T2","Fiesta", "Focus", "Mustang", "TempCar" ] },
        { "name":"Honda", "models":[ "320", "X3", "X5" ] },
        { "name":"Hyudai", "models":[ "500", "Panda","550" ] }
      ]
    }
  $(document).ready(function(){

      var index = 1;
      var table_body = '<table border="1" id="example" class = "table table-hover"><thead><tr><th>Sr No</th><th>Cars</th><th>Models</th><th></th></tr></thead><tbody>';
      for(j in json_obj.cars){

        for(i =0;i<json_obj.cars[j].models.length; i++){

          table_body+='<tr>';

          table_body +='<td>';
          table_body +=index;
          index += 1;
          table_body +='</td>';
          table_body +='<td>';
          table_body +=json_obj.cars[j]["name"];

          table_body +='</td>';

          table_body +='<td>';
          table_body +=json_obj.cars[j].models[i];
          table_body +='</td>';


          table_body +='<td>';

          var buttonId = "updateButton" + index;
          table_body += "<div class='container'><button class='btn .btnView' id="+buttonId+" name = "+json_obj.cars[j].models[i]+" onclick='update("+index+");'><b>View</b></button></div>";
          table_body +='</td>';
          table_body+='</tr>';
        }
      }
      table_body+='</tbody></table>';
      $("#tableDiv").html(table_body);
      //display data..........
    });
    // for search function.................................. only............................
    $("#search").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("table tr").filter(function(index) {
        if(index>0){
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        }
      });
    });
}





  function displayDataCard1() {

    var json_obj  = {
        "name":"John",
        "age":30,
        "cars": [
          { "name":"Ford", "models":[  "T2","Fiesta", "Focus", "Mustang", "TempCar" ] },
          { "name":"BMW", "models":[ "320", "X3", "X5" ] },
          { "name":"Fiat", "models":[ "500", "Panda","550" ] }
        ]
      }
    $(document).ready(function(){

        var index = 1;
        var table_body = '<table border="1" id="example" class = "table table-hover"><thead><tr><th>Sr No</th><th>Cars</th><th>Models</th><th></th></tr></thead><tbody>';
        for(j in json_obj.cars){

          for(i =0;i<json_obj.cars[j].models.length; i++){

            table_body+='<tr>';

            table_body +='<td>';
            table_body +=index;
            index += 1;
            table_body +='</td>';
            table_body +='<td>';
            table_body +=json_obj.cars[j]["name"];

            table_body +='</td>';

            table_body +='<td>';
            table_body +=json_obj.cars[j].models[i];
            table_body +='</td>';


            table_body +='<td>';

            var buttonId = "updateButton" + index;
            table_body += "<div class='container'><button class='btn' id="+buttonId+" name = "+json_obj.cars[j].models[i]+" onclick='update("+index+");'><b>View</b></button></div>";
            table_body +='</td>';
            table_body+='</tr>';
          }
        }
        table_body+='</tbody></table>';
        $("#tableDiv").html(table_body);
        //display data..........
      });
      // for search function.................................. only............................
      $("#search").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("table tr").filter(function(index) {
          if(index>0){
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
          }
        });
      });
  }
