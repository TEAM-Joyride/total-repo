import { api } from "@/lib/api.js"

// 이메일 정규 표현식
export function isEmailValidated(email) {
  return /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/i.test(email);
}

// 닉네임 정규 표현식 (10글자 이하, 한글 자모 단독 불가)
export function isNicknameValidated(nickname) {
  return /^(?!.*[ㄱ-ㅎㅏ-ㅣ])[A-Za-z0-9가-힣]{1,10}$/.test(nickname);
}

// 비밀번호 정규 표현식 (8자 이상, 숫자1, 특문1 포함)
export function isPasswordValidated(password) {
  return /^(?=.*[0-9])(?=.*[a-z])(?=.*[^a-zA-Z0-9])(?=\S+$).{8,}$/.test(password);
}

// 아이디 정규 표현식 (영어, 숫자를 포함한 1자 이상 20자 이하)
export function isUsernameValidated(username) {
  return /^[a-zA-Z0-9]{1,20}$/.test(username);
}

export function isNicknameDuplicated(nickname) {
  return api.get("/api/members/duplicate/nickname", {params: {
      nickname: nickname
    }})
    .then(response => {
      if (window.confirm("사용할 수 있는 닉네임입니다. \n사용하시겠습니까?")) {
        return true;
      }
      return false;
    })
    .catch(() => {
      return false;
    })
}

export function getAuthCode(email) {
  return api.get("/api/members/auth-email", {params: {
      email: email,
    }})
}

export function checkAuthCode(email, auth_code) {
  return api.post("/api/members/auth-email", {
    email,
    auth_code,
  })
    .then(response => {
      return true
    })
    .catch(err => {
      return false
    })
}

export function signUp(username, nickname, email, password) {
  return api.post("/api/members/signup", {
    username,
    nickname,
    email,
    password,
  })
    .then(() => {
      return true
    })
    .catch(err => {
      return Promise.reject(err)
    })
}

export function login(username, password) {
  return api.post("/api/members/login", {
    username,
    password,
  })
    .then(response => {
      return true
    })
    .catch(err => {
      return Promise.reject(err)
    })
}

export function kakaoLogin() {
  window.location.href = "http://localhost:8080/oauth2/authorization/kakao?redirect_uri=http://localhost:5173&mode=login";
}
export function naverLogin() {
  window.location.href = "http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:5173&mode=login";
}