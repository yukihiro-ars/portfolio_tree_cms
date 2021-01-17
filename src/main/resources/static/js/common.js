var ajaxHelper = {
    doPost : function(form) {
        $.ajax({
            url : form.attr("action"),
            method : form.attr("method"),
            data : form.serialize(),
            contentType : "application/x-www-form-urlencoded",
            beforeSend : function () {
                $(".errorMsg").remove();
            }
        }).done(function() {
            // TODO 処理成功時のメッセージ
            console.log(arguments);
            alert("success!!");
        }).error(function(jqXHR) {
            // エラーメッセージ出力
            if (jqXHR && jqXHR.responseJSON) {
                Object.keys(jqXHR.responseJSON)
                    .map(function(elm, idx, arr) {
                        $("#"+ elm).after(
                            "<span class='errorMsg'>"
                            + jqXHR.responseJSON[elm]
                            + "</span>");
                    });
                console.log(jqXHR.responseJSON);
            }
            alert("error!!");
        });
    }
};
