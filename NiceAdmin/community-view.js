const urlParams = new URL(location.href).searchParams;
const no = urlParams.get('no');

fetch(`http://localhost:8080/community/${63}`)
.then((response) => {
  return response.json();
})
.then((data) => {

  console.log(data);
    if (data.status == 'failure') {
      alert('서버 요청 오류!');
      console.log(data);
      return;
    }
  document.querySelector('#title').value = data.data.title;
  document.querySelector('#category').value = data.data.category;
  document.querySelector('#doctorName').value = data.data.doctorName;
  document.querySelector('#createdDate').value = data.data.createdDate;
  document.querySelector('#content').value = data.data.content;
  if(data.photo.length >0 ) {
    $(".comphoto")[0].src = data.photo[0].imgUrl;
  } else {
    $(".photocover").html("")
  }
  // console.log(data.photo[0].imgUrl)
})
.catch((err) => {
  alert('서버 요청 오류!');
  console.log(err);
});
  