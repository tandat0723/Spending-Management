/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function deleteUser(endpoint, id) {
    if (confirm("Bạn chắc chắn xóa người dùng này?") === true) {
        let s = document.getElementById(`spinner${id}`);
        //let d = document.getElementById(`delete${id}`);
        s.style.display = "inline-block";
        //d.style.display = "none";
        
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            s.style.display = "none";
            console.info(res);
            if (res.status === 204) {
                document.getElementById(`user${id}`).style.display = "none";
            } else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
                //d.style.display = "inline-block";
        });
    }
}