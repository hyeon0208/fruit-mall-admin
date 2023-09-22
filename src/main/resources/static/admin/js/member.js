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