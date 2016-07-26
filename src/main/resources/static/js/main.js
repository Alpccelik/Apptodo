$(document).ready(function () {
    $(".todo-name").editable({
        success: function (response, newValue) {
            window.location.replace("/todo/" + $($(this).parents("form")).attr("id") + "?name=" + newValue);
        }
    });
    $(".item-content").editable({
        success: function (response, newValue) {
            window.location.replace("/todo/item/" + $($(this).siblings("input")).attr("id") + "?content=" + newValue);
        }
    });
    $("input[type=checkbox]").change(function () {
        window.location.replace("/todo/item/" + $(this).attr("id") + "?checked=" + $(this).is(":checked"));
    });
});