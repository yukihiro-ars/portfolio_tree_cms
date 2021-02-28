const ajaxHelper = {
    doRequest : (form, url) => {
        $.ajax({
            url : url ? url : form.attr("action"),
            type : form.attr("method"),
            data : form.serialize(),
            contentType : "application/x-www-form-urlencoded",
            beforeSend : () => {
                $(".errorMsg").remove();
            }
        }).done(() => {
            alert("success!!");
        }).fail((jqXHR) => {
            console.log(jqXHR);
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
},
StringHelper = {
    trimSlash : (path) => {
        let st = path.startsWith("/") ? 1 : 0,
            ed = path.endsWith("/") ? path.length - (1 + st) : path.length;
        return path.substr(st, ed);
    }
};
