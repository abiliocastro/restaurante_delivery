$("#menu-butao").click(function() {
    $("#menu").toggle();
});

var verMais = '<h1 id="ver-mais">Ver Mais</h1>';
var berre = "<br>"
$(".escondido:first").before(verMais);
$("#ver-mais").before(berre);

$("#ver-mais").click(function() {
    $(".escondido").css("display", "inline-block");
    $("#ver-mais").css("display", "none");
});

$("#endereco-pedido").hide();
$("#btn-continuar").click(function() {
	$("#endereco-pedido").show();
});