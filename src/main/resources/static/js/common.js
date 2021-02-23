const ajaxHelper = {
    doRequest : (form) => {
        $.ajax({
            url : form.attr("action"),
            type : form.attr("method"),
            data : form.serialize(),
            contentType : "application/x-www-form-urlencoded",
            beforeSend : () => {
                $(".errorMsg").remove();
            }
        }).done(() => {
            console.log(arguments);
            // TODO 処理成功時のメッセージ
            alert("success!!");
        }).fail((jqXHR) => {
            // エラーメッセージ出力
            if (jqXHR && jqXHR.responseJSON) {
                Object
                    .keys(jqXHR.responseJSON)
                    .map((elm, idx, arr) => {
                        $("#"+ elm).after(
                            "<span class='errorMsg'>"
                            + jqXHR.responseJSON[elm]
                            + "</span>");
                    });
                console.log(jqXHR.responseJSON);
            }
            // TODO システムエラーのハンドリングを検討
            alert("error!!");
        });
    }
};
