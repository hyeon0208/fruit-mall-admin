let status, duration, startDate, endDate, category, searchText = null;
let reviewPageNum = 1;
let reviewPageSize = 5;

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

// 답변 미답변 버튼
$(document).on("click", ".replyCondBtn", (e) => {
    let btn = $(e.currentTarget);
    $(".replyCondBtn").css("background-color", "");
    $(".replyCondBtn").css("color", "");

    btn.css("background-color", "rgb(39 33 33)");
    btn.css("color", "white");

    status = btn.text();
    reviewPageNum = 1;
    newReviewList();
});


// 기간검색 버튼
$(document).on("click", ".durationBtn", (e) => {
    let btn = $(e.currentTarget);

    $(".durationBtn").not(btn).each((i, e) => {
        $(e).data("click-cnt", 0);
    });
    let clickCount = btn.data("click-cnt") || 0;
    clickCount += 1;
    btn.data("click-cnt", clickCount);

    $(".durationBtn").css("background-color", "");
    $(".durationBtn").css("color", "");
    if (clickCount % 2 === 0) {
        $('#date1').prop('disabled', false);
        $('#date2').prop('disabled', false);
    } else {
        duration = btn.val();
        reviewPageNum = 1;
        btn.css("background-color", "rgb(39 33 33)");
        btn.css("color", "white");
        newReviewList();
        $('#date1').prop('disabled', true);
        $('#date2').prop('disabled', true);
    }
});

// 날짜항목 버튼
$(document).on("change", "#date1, #date2", () => {
    startDate = $('#date1').val();
    endDate = $('#date2').val();

    if (startDate && endDate) {
        const start = new Date(startDate);
        const end = new Date(endDate);
        if (end < start) {
            return alert("기간 설정을 다시 해주세요.\n종료일이 시작일보다 이전입니다.");
        }
        duration = null;
        reviewPageNum = 1;
        newReviewList();
    }
});

// 검색어 입력 버튼
$(document).on("click", "#searchReview", () => {
    searchText = $("#searchText").val();
    category = $("#categories").val();
    reviewPageNum = 1;
    newReviewList();
});

// 엔터키 입력 이벤트
$(document).on("keyup", "#searchText", (e) => {
    if (e.keyCode == 13) {
        searchText = $("#searchText").val();
        category = $("#categories").val();
        reviewPageNum = 1;
        newReviewList();
    }
});

function newReviewList() {
    axios({
        method: "get",
        url: "/admin/review/searchFilter",
        params: {
            status: status,
            duration: duration,
            startDate: startDate,
            endDate: endDate,
            category: category,
            searchText: searchText,
            pageNum: reviewPageNum,
            pageSize: reviewPageSize
        }
    }).then((res) => {
        const reviews = res.data.list;
        const pageInfo = res.data;

        $('.box03 .bold').text(pageInfo.size);

        // .product-table 내부의 tbody 내부 내용 교체
        const newReviewPage = reviews.map((review) => {
            return `<tr class="review-table" data-order-number="${review.orderNumber}" data-review-id="${review.reviewId}">
                <td>
                    <input class="pageChk" type="checkbox">
                </td>
                <td>${review.num}</td>
                <td>${review.reviewStatus}</td>
                <td>
                    <a href="/detail/${review.productId}" class="productName">${review.productName}</a>
                </td>
                <td class="reviewContents">${review.reviewContents}</td>
                <td>${review.userEmail}</td>
                <td class="userName">${review.userName}</th>
                <td class="reviewCreatedAt">${new Date(review.reviewCreatedAt).toLocaleDateString("ko-KR", { year: 'numeric', month: '2-digit', day: '2-digit' }).replaceAll(". ", ".").substring(0, 10)}</th>

                <td>
                ${review.reviewStatus === '미답변' ?
                    `<button style='background-color:#173877; color:white'>답변하기</button>` :
                    `<button>답변보기</button>`}
                </td>
            </tr>`
        });
        $('.product-table tbody').empty().append(newReviewPage);


        // .pagination 클래스 태그 내부 내용 교체
        const paginationDiv = $('.pagination').empty();
        const totalData = pageInfo.pages; // 총 데이터 수
        const pageNumberList = pageInfo.navigatepageNums; // 페이지 번호들의 순서를 담은 배열
        const currentPage = pageInfo.pageNum;

        // 이전 페이지 버튼
        if (pageInfo.hasPreviousPage) {
            const prevBtn = $("<a>")
                .attr('href', '#')
                .addClass("review-prevBtn")
                .attr('value', pageInfo.prePage)
                .html('<span class="material-symbols-outlined">chevron_left</span>');
            paginationDiv.append(prevBtn);
        }

        // 페이지 번호 버튼
        pageNumberList.forEach((pageNumber) => {
            if (pageNumber <= totalData) {
                const numberBtn = $("<a>")
                    .text(pageNumber)
                    .attr('href', '#')
                    .addClass('review-numberBtn')
                    .attr('value', pageNumber);

                if (pageNumber === currentPage) {
                    numberBtn.addClass("bold").addClass("large-text").css("font-size", "16px");
                }
                paginationDiv.append(numberBtn);
            }
        });

        // 다음 페이지 버튼
        if (pageInfo.hasNextPage) {
            const nextBtn = $("<a>")
                .attr('href', '#')
                .addClass("review-nextBtn")
                .attr('value', pageInfo.nextPage)
                .html('<span class="material-symbols-outlined">chevron_right</span>');
            paginationDiv.append(nextBtn);
        }
    });
}

// 이전 페이지 버튼
$(document).on('click', '.review-prevBtn', () => {
    reviewPageNum -= 1;
    newReviewList();
});

// 페이지 번호 버튼
$(document).on('click', '.review-numberBtn', (e) => {
    reviewPageNum = parseInt($(e.currentTarget).attr("value"));
    newReviewList();
});

// 다음 페이지 버튼
$(document).on('click', '.review-nextBtn', () => {
    reviewPageNum += 1;
    newReviewList();
});