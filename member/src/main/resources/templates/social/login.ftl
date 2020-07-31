<button onclick="popupKakaoLogin()">KakaoLogin</button>
<button onclick="popupNaverLogin()">NaverLogin</button>
<script>
    function popupKakaoLogin() {
        window.open('${kakaoLoginUrl}', 'popupKakaoLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')
    }
    function popupNaverLogin() {
        window.open('${naverLoginUrl}', 'popupNaverLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')
    }
</script>