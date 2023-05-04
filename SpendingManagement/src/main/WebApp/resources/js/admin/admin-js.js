/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function deleteUser(endpoint, id) {
    if (confirm("Bạn chắc chắn xóa người dùng này?") === true) {
        let s = document.getElementById(`spinner${id}`);
        let d = document.getElementById(`delete${id}`);
        s.style.display = "inline-block";
        d.style.display = "none";

        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            s.style.display = "none";
            console.info(res);
            if (res.status === 204) {
                //document.getElementById(`user${id}`).style.display = "none";
                alert("Tài khoản sẽ bị xóa vĩnh viễn sau 30 ngày!");
                location.reload();
            } else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
            d.style.display = "inline-block";
        });
    }
}
function sortColumn(header) {
    // Lấy thông tin bảng và các hàng
    var table = $(header).closest('table');
    var rows = table.find('tbody > tr').get();

    // Lấy vị trí của cột cần sắp xếp
    var columnIndex = $(header).index();

    // Đảo chiều thứ tự sắp xếp nếu cột đang được sắp xếp theo chiều tăng dần
    var isAscending = $(header).hasClass('asc');
    if (isAscending) {
        $(header).removeClass('asc');
        $(header).addClass('desc');
        $(header).find('.sort-icon').html('&#x2193;');
        rows.reverse();
    } else {
        $(header).removeClass('desc');
        $(header).addClass('asc');
        $(header).find('.sort-icon').html('&#x2191;');
        rows.reverse();
    }

    // Thay đổi thứ tự các hàng trong bảng
    $.each(rows, function (index, row) {
        if (index > 0) {
            table.children('tbody').append(row);
        }
    });
    // Thêm đoạn code xóa icon và class của thẻ th
    var siblings = $(header).parent().siblings();
    siblings.removeClass('asc desc');
    siblings.find('.sort-icon').html('');
}

function previewImage(input) {
    let a = document.getElementById("avatar-old");
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result);
            $('#preview-container').show(); // hiển thị thẻ div khi có hình ảnh được chọn
            a.style.display = "none";
        }
        reader.readAsDataURL(input.files[0]); // convert to base64 string
    }
}
const v = document.querySelectorAll('.js-view-account');
const view = document.querySelector('.js-modal-view');
const modalClosedd = document.querySelector('.js-modal-view-close');
const modalContainerss = document.querySelector('.js-modal-view-container');
function showAccountView() {
    // alert('Modal opened successfully');
    view.classList.add('js-modal-view-open');
}
function hideAccountView() {
    view.classList.remove('js-modal-view-open');
}
function adminAccountView(endpointview) {
    fetch(endpointview, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/xml'
        }
    }).then(res =>
        res.text()
    ).then(data => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(data, 'application/xml');
        const json = xmlToJson(xml);
        console.log(json);
        let js = document.getElementById("js-modal-view");
        js.innerHTML = `
        <div class="col-lg-8">
            <div class="about-text go-to">
                <h3 class=" mb-4">
                    ${json.user.fullname}
                </h3>
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Email</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.user.email}</p>
                    </div>
                </div>
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Số điện thoại</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.user.phone}</p>
                    </div>
                </div>
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Username</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.user.username}</p>
                    </div>
                </div>  
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Password</h5>
                    </div>
                    <div class="col-md-7 overflow-hiddenpass">
                        <p>${json.user.password}</p>
                    </div>
                </div>  
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Trạng thái</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.user.active.statusName}</p>
                    </div>
                </div>  
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Loại tài khoản</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.user.userRole.role}</p>
                    </div>
                </div>  
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Ngày tạo</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.user.joinedDate}</p>
                    </div>
                </div>  
            </div>
        </div>
        <div class="col-lg-4">
            <div class="about-avatar d-flex justify-content-center">
                    <img src="${json.user.avatar}" class="img-view rounded">
            </div>
        </div>
    `;
        for (const btn of v) {
            btn.addEventListener('click', showAccountView);
        }
        modalClosedd.addEventListener('click', hideAccountView);
        view.addEventListener('click', hideAccountView);
        modalContainerss.addEventListener('click', function (event) {
            event.stopPropagation();
        });
    }
    ).catch(error => {
        console.info(error);
    });
}

function xmlToJson(xml) {
    var obj = {};
    if (xml.nodeType == 1) {
        if (xml.attributes.length > 0) {
            obj["@attributes"] = {};
            for (var j = 0; j < xml.attributes.length; j++) {
                var attribute = xml.attributes.item(j);
                obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
            }
        }
    } else if (xml.nodeType == 3) {
        obj = xml.nodeValue.trim();
    }
    if (xml.hasChildNodes()) {
        for (var i = 0; i < xml.childNodes.length; i++) {
            var item = xml.childNodes.item(i);
            var nodeName = item.nodeName;
            if (nodeName == "#text") {
                obj = item.nodeValue.trim();
                continue;
            }
            if (typeof (obj[nodeName]) == "undefined") {
                obj[nodeName] = xmlToJson(item);
            } else {
                if (typeof (obj[nodeName].push) == "undefined") {
                    var old = obj[nodeName];
                    obj[nodeName] = [];
                    obj[nodeName].push(old);
                }
                obj[nodeName].push(xmlToJson(item));
            }
        }
    }
    return obj;
}

const btns = document.querySelectorAll('.js-edit-spending');
function showCart() {
    // alert('Modal opened successfully');
    view.classList.add('js-modal-open');
}
function hideCart() {
    view.classList.remove('js-modal-open');
}
function adminSpendingView(endpointview) {
    debugger;
    fetch(endpointview, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/xml'
        }
    }).then(res =>
        res.text()
    ).then(data => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(data, 'application/xml');
        const json = xmlToJson(xml);
        console.log(json);
        let js = document.getElementById("js-modal-view");
        js.innerHTML = `
        <div class="col-lg-8">
            <div class="about-text go-to">
                <h3 class=" mb-4">
                    ${json.personalTransaction.userId.fullname}
                </h3>
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Loại giao dịch</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.personalTransaction.transactionType.typename}</p>
                    </div>
                </div>
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Mục đích</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.personalTransaction.purpose}</p>
                    </div>
                </div>
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Mô tả chi tiết</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.personalTransaction.description}</p>
                    </div>
                </div>  
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Số tiền</h5>
                    </div>
                    <div class="col-md-7 overflow-hiddenpass">
                        <p>${json.personalTransaction.price} VND</p>
                    </div>
                </div>  
                <div class="row mb-2">
                    <div class="col-md-5">
                        <h5>Ngày tạo</h5>
                    </div>
                    <div class="col-md-7">
                        <p>${json.personalTransaction.date}</p>
                    </div>
                </div>  
            </div>
        </div>
        <div class="col-lg-4">
            <div class="about-avatar d-flex justify-content-center">
                    <img src="${json.personalTransaction.userId.avatar}" class="img-view rounded">
            </div>
        </div>
    `;
    for (const btn of btns) {
        btn.addEventListener('click', showCart);
    }
    modalClosedd.addEventListener('click', hideCart);
    view.addEventListener('click', hideCart);
    modalContainerss.addEventListener('click', function (event) {
        event.stopPropagation();
    });
    }
    ).catch(error => {
        console.info(error);
    });
}