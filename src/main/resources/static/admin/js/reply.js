$(() => {
    // 답글 작성 모달창 작성 글자 수 표시
    $("#replyContent").on("input", () => {
        const inputLength = $("#replyContent").val().length;
        $("#content__count").text(inputLength + " / 500");
    });

    // 2023.09.23 형식으로 날짜 표시
    $(".reviewCreatedAt").each((i, e) => {
        const date = new Date($(e).text());
        const formattedDate = date.toLocaleDateString('ko-KR', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
        });
        $(e).text(formattedDate.replaceAll(". ", ".").substring(0, 10));
    });
});

// 답변 달기 클릭
$(document).on("click", ".add_reply", (e) =>  {
    const target = $(e.currentTarget);
    const orderNumber = target.closest("tr").data("order-number");
    const reviewId = target.closest("tr").data("review-id");
    const productName = target.closest("tr").find(".productName").text();
    const reviewContents = target.closest("tr").find(".reviewContents").text();
    const userName = target.closest("tr").find(".userName").text();
    const reviewCreatedAt = new Date(target.closest("tr").find(".reviewCreatedAt").text());
    const dateOptions = { year: 'numeric', month: '2-digit', day: '2-digit' };
    const timeOptions = { hour12: false, hour: '2-digit', minute:'2-digit', second:'2-digit' };
    const reviewCreatedAtFormatted = `${reviewCreatedAt.toLocaleDateString('ko-KR', dateOptions)} (${reviewCreatedAt.toLocaleTimeString('ko-KR', timeOptions)})`;
    $("#modal-orderNumber").text(`주문번호 ${orderNumber}`);
    $("#modal-productName").text(productName);
    $("#modal-contents").val(reviewContents);
    $("#modal-name-time").text(`${userName}  |  ${reviewCreatedAtFormatted} 작성`);

    $(".admin__review").show();
    $(".admin__review button:eq(0)").on("click", () => {
        $(".admin__review__cancel").show();
        $(".admin__review__cancel button:eq(0)").on("click", () => {
            $("#replyContent").val("");
            $("#content__count").text($("#review_contents").text().length + "/500")
            $(".admin__review").hide();
            $(".admin__review__cancel").hide();
        });

        $(".admin__review__cancel button:eq(1)").on("click", () => {
            $(".admin__review__cancel").hide();
        });
    });

    $(".admin__review button:eq(1)").on("click", () => {
        axios({
            url: "/api/v1/reply",
            method: "post",
            data: {
                reviewContents: $("#replyContent").val(),
                reviewId: reviewId
            },
            dataType: "json",
            headers: {'Content-Type': 'application/json'}
        }).then(res => {
            $(".admin__review__confirm").show();
            $(".admin__review__confirm button").on("click", () => {
                $(".admin__review__confirm").hide();
                window.location.href = "/admin/review";
            });
        });
    });
});

// 답변 보기 클릭
$(document).on("click", ".show_reply", (e) => {
    const target = $(e.currentTarget);
    const orderNumber = target.closest("tr").data("order-number");
    const reviewId = target.closest("tr").data("review-id");
    const productName = target.closest("tr").find(".productName").text();
    const reviewContents = target.closest("tr").find(".reviewContents").text();
    const userName = target.closest("tr").find(".userName").text();
    const reviewCreatedAt = new Date(target.closest("tr").find(".reviewCreatedAt").text());
    const dateOptions = { year: 'numeric', month: '2-digit', day: '2-digit' };
    const timeOptions = { hour12: false, hour: '2-digit', minute:'2-digit', second:'2-digit' };
    const reviewCreatedAtFormatted = `${reviewCreatedAt.toLocaleDateString('ko-KR', dateOptions)} (${reviewCreatedAt.toLocaleTimeString('ko-KR', timeOptions)})`;
    $("#modal-orderNumber").text(`주문번호 ${orderNumber}`);
    $("#modal-productName").text(productName);
    $("#modal-contents").val(reviewContents);
    $("#modal-name-time").text(`${userName}  |  ${reviewCreatedAtFormatted} 작성`);

    axios({
        url: "/api/v1/show-reply",
        method: "get",
        params: { reviewId: reviewId }
    }).then(res => {
        $("#replyContent").val(res.data);
    });

    $(".admin__review").show();
    $(".admin__review button:eq(0)").on("click", () => {
        $(".admin__review").hide();
    });

    $(".admin__review button:eq(1)").on("click", () => {
        axios({
            url: "/api/v1/update-reply",
            method: "post",
            data: {
                reviewContents: $("#replyContent").val(),
                reviewId: reviewId
            },
            dataType: "json",
            headers: {'Content-Type': 'application/json'}
        }).then(res => {
            $(".admin__review__confirm").show();
            $(".admin__review__confirm button").on("click", () => {
                $(".admin__review__confirm").hide();
                window.location.href = "/admin/review";
            });
        });
    });
});