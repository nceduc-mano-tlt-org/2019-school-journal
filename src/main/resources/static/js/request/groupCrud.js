$(document).ready(function() {

    $(".createGroup").click(function() {
        var groupData = $(this).parents("form").serialize();
        $.ajax({
            url: "/group/create",
            type: "POST",
            data: groupData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

    $(".updateGroup").click(function() {
        var groupData = $(this).parents("form").serialize();
        $.ajax({
            url: "/group/update",
            type: "PUT",
            data: groupData,
            dataType: "JSON",
            success: function(response) {
               location.reload();
            }
        });
    });

    $(".deleteGroup").click(function() {
        var groupData = $(this).parents("form").serialize();
        $.ajax({
            url: "/group/delete",
            type: "DELETE",
            data: groupData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

});