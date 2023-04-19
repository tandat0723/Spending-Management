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
                document.getElementById(`user${id}`).style.display = "none";
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