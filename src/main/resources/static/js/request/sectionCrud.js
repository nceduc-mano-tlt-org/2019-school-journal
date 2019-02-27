$(document).ready(function() {

    $(".createSection").click(function() {
        var sectionData = $(this).parents("form").serialize();
        $.ajax({
            url: "/section/create",
            type: "POST",
            data: sectionData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

    $(".updateSection").click(function() {
        var sectionData = $(this).parents("form").serialize();
        $.ajax({
            url: "/section/update",
            type: "PUT",
            data: sectionData,
            dataType: "JSON",
            success: function(response) {
               location.reload();
            }
        });
    });

    $(".deleteSection").click(function() {
        var sectionData = $(this).parents("form").serialize();
        $.ajax({
            url: "/section/delete",
            type: "DELETE",
            data: sectionData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

});