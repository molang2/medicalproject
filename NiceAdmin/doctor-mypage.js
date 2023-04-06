

fetch("http://192.168.0.7:8080/d_no", {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({ // 스프링에 전달할 값
    no: str
  })
})
.then(response => response.json())
.then(data => {
  