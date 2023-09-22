let userStatus, userSearchCategory, userSearchText = null;
let userPageNum = 1;
let userPageSize = 5;

$(document).on("click", ".deliveryShowBtn", (e) => {
    const userIdNo = $(e.currentTarget).data("user-id");

    axios({
        url: `/api/v1/deliveries/${userIdNo}`,
        method: "get"
    }).then(res => {
        const firstDelivery = res.data[0];
        $(".bottom ul li:nth-child(1) span:nth-child(2)").text(firstDelivery.deliveryName);
        $(".bottom ul li:nth-child(2) span:nth-child(2)").text(firstDelivery.userName);
        $(".bottom ul li:nth-child(3) span:nth-child(2)").text(firstDelivery.phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3"));
        $(".bottom ul li:nth-child(4) span:nth-child(2)").text(firstDelivery.zipcode + " " + firstDelivery.address);

        $(".admin__delivery").show();

        $(".admin__delivery .middie button").click((e) => {
            const index = $(e.currentTarget).index();
            if (res.data.length > 0) {
                const deliveryInfo = res.data[index];

                $(".bottom ul li:nth-child(1) span:nth-child(2)").text(deliveryInfo.deliveryName);
                $(".bottom ul li:nth-child(2) span:nth-child(2)").text(deliveryInfo.userName);
                $(".bottom ul li:nth-child(3) span:nth-child(2)").text(deliveryInfo.phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3"));
                $(".bottom ul li:nth-child(4) span:nth-child(2)").text(deliveryInfo.zipcode + " " + deliveryInfo.address);
            }
        });

        $('.admin__delivery__buttons button').click(function() {
            $('.admin__delivery').hide();
        });
    }).catch(err => {
        alert("배송정보가 없습니다.");
    });
});

$(document).on("click", ".userStatusBtn", (e) => {
    let btn = $(e.currentTarget);
    $(".userStatusBtn").css("background-color", "");
    $(".userStatusBtn").css("color", "");

    btn.css("background-color", "rgb(39 33 33)");
    btn.css("color", "white");

    userStatus = btn.text();
    userPageNum = 1;
    newMemberList();
});

$(document).on("click", "#searchUser", () => {
    userSearchText = $("#userSearchText").val();
    userSearchCategory = $("#userCategories").val();
    userPageNum = 1;
    newMemberList();
});

$(document).on("click", ".withdrawBtn", (e) => {
    const userIdNo = $(e.currentTarget).data("user-id");

    $(".admin__cancel").show();
    $(".admin__cancel button:eq(0)").on("click", () => {
        $(".admin__cancel").hide();
    });

    $(".admin__cancel button:eq(1)").on("click", () => {
        axios({
            url: "/api/v1/withdraw",
            method: "post",
            data: {
                userIdNo: userIdNo
            },
            dataType: "json",
            headers: {'Content-Type': 'application/json'}
        }).then(res => {
            $(".admin__cancel__confirm").show();

            $(".admin__cancel__confirm button").on("click", () => {
                $(".admin__cancel__confirm").hide();
                $(".admin__cancel").hide();
                window.location.href = "/admin/member"
            });
        });
    });
});

function newMemberList() {
    axios({
        method: "get",
        url: "/api/v1/member/search",
        params: {
            userStatus: userStatus,
            userSearchCategory: userSearchCategory,
            userSearchText: userSearchText,
            userPageNum: userPageNum,
            userPageSize: userPageSize
        }
    }).then((res) => {
        const userInfos = res.data.list;
        const pageInfo = res.data;

        $('.box03 .bold').text(pageInfo.size);

        // 새로운 테이블 내용 추가
        const newUserPage = userInfos.map((userInfo) => {
            let statusColumn;

            if (userInfo.userStatus === '활동중') {
                statusColumn = `<td><button class="withdrawBtn" data-user-id="${userInfo.userIdNo}">탈퇴</button></td>`;
            } else if (userInfo.userStatus === '탈퇴') {
                statusColumn = `<td><text>-</text></td>`;
            }

            return `
                <tr>
                    <td>
                        <input type="checkbox">
                    </td>
                    <td>${userInfo.num}</td>
                    <td>${userInfo.userStatus}</td>
                    <td>${userInfo.userName}</td>
                    <td>${userInfo.userEmail}</td>
                    <td>${userInfo.orderCount}</td>
                    <td><button class="deliveryShowBtn" data-user-id="${userInfo.userIdNo}">보기</button></td>  
                    <td class="time">${new Date(userInfo.userCreatedAt).toLocaleDateString("ko-KR", { year: 'numeric', month: '2-digit', day: '2-digit' }).replaceAll(". ", ".").substring(0, 10)}
                    ${statusColumn}
                </tr>`;

        });
        $('#admin_member .box04 tbody').empty().append(newUserPage);

        // .pagination 클래스 태그 내부 내용 교체
        const paginationDiv = $('#admin_member .pagination').empty();
        const totalData = pageInfo.pages;
        const pageNumberList = pageInfo.navigatepageNums;
        const currentPage = pageInfo.pageNum;

        // 이전 페이지 버튼
        if (pageInfo.hasPreviousPage) {
            const prevBtn = $("<a>")
                .attr('href', '#')
                .addClass("user-prevBtn")
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
                    .addClass('user-numberBtn')
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
                .addClass("user-nextBtn")
                .attr('value', pageInfo.nextPage)
                .html('<span class="material-symbols-outlined">chevron_right</span>');
            paginationDiv.append(nextBtn);
        }
    });
}

// 이전 페이지 버튼
$(document).on('click', '.user-prevBtn', () => {
    userPageNum -= 1;
    newMemberList();
});

// 페이지 번호 버튼
$(document).on('click', '.user-numberBtn', (e) => {
    userPageNum = parseInt($(e.currentTarget).attr("value"));
    newMemberList();
});

// 다음 페이지 버튼
$(document).on('click', '.user-nextBtn', () => {
    userPageNum += 1;
    newMemberList();
});