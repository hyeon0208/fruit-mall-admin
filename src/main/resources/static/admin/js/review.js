
// 선택 상품 삭제
$(document).on("click", "#del-reviewBtn", () => {
    const checkedReviews = $("input[type='checkbox']:checked");

    checkedReviews.each((index, checkbox) => {
        const reviewId = $(checkbox).closest("tr").data("review-id");
        axios({
            method: "post",
            url: `/admin/review/delete/${reviewId}`,
            dataType: "json",
            headers: {'Content-Type': 'application/json'}
        }).then(res => {
            alert("해당 리뷰가 삭제되었습니다.")
        });
    });
});