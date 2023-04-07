
tbody = document.querySelector('#community-list');


fetch('http://localhost:8080/community/list')
  .then((response) => {
    return response.json();
  })
  .then((data) => {
   
    var html = '';
    for (var row of data.data) {
      html += `<tr>
          <td>${row.no}</td>
          
          <td><a href="community-view.html?no=${row.no}">${row.title}</a></td>
          <td>${row.doctorName}</td>
          <td>${row.category}</td>
          <td>${row.viewCnt}</td>
          <td>${row.createdDate}</td>
          </tr>\n`;
    }
    console.log(data);
    tbody.innerHTML = html;
  })
  .catch((err) => {
    alert('서버 요청 오류!');
    console.log(err);
  });
