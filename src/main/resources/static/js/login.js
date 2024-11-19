function submitLogin() {
    // Lấy giá trị từ các trường input
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    // Kiểm tra nếu thông tin không hợp lệ (đảm bảo không rỗng)
    if (!username || !password) {
        alert('Please enter both username and password.');
        return;
    }

    fetch('/auth/login', {
        method: 'POST', // Cách gửi yêu cầu POST
        headers: {
            'Content-Type': 'application/json', // Đảm bảo gửi dữ liệu dưới dạng JSON
        },
        body: JSON.stringify({
            username: username,
            password: password,
        }),
    })
        .then(response => response.json()) // Chuyển đổi phản hồi thành JSON
        .then(data => {
            const { code, result } = data;

            // Kiểm tra nếu response trả về code là 0 và authentication thành công
            if (code === 0 && result.authenicated) {
                const token = result.token;
                const username = result.username;
                // Lưu token vào cookie
                document.cookie = `token=${token}; path=/; secure; SameSite=Strict`;
                document.cookie = `username=${username}; path=/; secure; SameSite=Strict`;
                document.cookie = `password=${passwork}; path=/; secure; SameSite=Strict`;
                alert('Login successful');
                window.location.href = '/'; // Chuyển hướng đến trang chủ
            } else {
                alert('Login failed: Invalid credentials or authentication issue.');
            }
        })
        .catch(error => {
            alert('Login failed: ' + (error.message || 'Unknown error occurred.'));
        });
}
