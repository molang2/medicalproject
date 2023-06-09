const tooltipTriggerList = document.querySelectorAll(
  '[data-bs-toggle="tooltip"]'
);
const tooltipList = [...tooltipTriggerList].map(
  (tooltipTriggerEl) => new bootstrap.Tooltip(tooltipTriggerEl)
);

// fetch(`http://175.106.99.31/qna/${1}`)
//   .then(response => response.json())
//   .then(data => {
//     console.log(data)
//     let lilist = [];
//     data.data.content.split(",").forEach(text => {
//       let content = text.split(":")[0];
//       let user = text.split(":")[1];
//       if(user == "질문자") {
//         lilist.push(<Lli text = {content} />)
//       }
//     })
//     return lilist;
//   })
//   .then(list => {
//     ReactDOM.createRoot(document.querySelector(".chat-list")).render(list)
// })

fetch(`http://175.106.99.31/auth/user`, {
  method: "GET",
})
  .then((response) => response.json())
  .then((data) => {
    if (data.status == "success") {
      //사용자 이름
      document.querySelector("#username").innerHTML =
        data.data.name + "(관리자)";
      return data.data;
    } else {
      location.href = "index.html";
    }
    return data.data;
  })
  .then((user) => {
    if (user.admin) {
      // myno = user.no
    } else {
      location.href = "index.html";
    }
  });

fetch(`http://175.106.99.31/auth/user`, {
  method: "GET",
})
  .then((response) => response.json())
  .then((data) => {
    if (data.status == "success") {
      //사용자 이름
      document.querySelector("#username").innerHTML =
        data.data.name + "(관리자)";
      return data.data;
    } else {
      location.href = "index.html";
    }
    return data.data;
  })
  .then((user) => {
    if (user.admin) {
      // myno = user.no
    } else {
      location.href = "index.html";
    }
  });

let no = new URLSearchParams(location.search).get("no");

reflash();
$(".chat-btn").click(() => {
  if ($(".chat-text").val().length > 1) {
    fetch("http://175.106.99.31/qna/admin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        // 스프링에 전달할 값
        content: $(".chat-text").val(),
        mno: Number(no),
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.status == "success") {
          $(".chat-text").val("");
          reflash();
        }
      });
  }
});

function Lli(params) {
  return (
    <li className="chat-left">
      <div>
        <span id="q-content">{params.text}</span>
        <br />
        <br />
        <span id="q-date">{params.date}</span>
      </div>
    </li>
  );
}

function Rli(params) {
  return (
    <li className="chat-right">
      <div>
        <span id="a-content">{params.text}</span>
        <br />
        <br />
        <span id="a-date">{params.date}</span>
      </div>
    </li>
  );
}

/*

<li className="chat-left">
  <div>
    <span>1</span>
  </div>
</li>

<li className="chat-right">
  <div>
    <span>1</span>
  </div>
</li>

*/
let aciveText = "";
function reflash() {
  fetch(`http://175.106.99.31/qna/admin/${no}`)
    .then((response) => response.json())
    .then((data) => {
      let lilist = [];
      if (data.data == null) {
        return;
      }
      aciveText = data.data.content;
      data.data.content.split(",").forEach((text) => {
        let content = text.split(":")[0];
        let user = text.split(":")[1];
        let date = text.split(":")[2];
        if (user == "질문자") {
          lilist.push(<Lli text={content} date={date} />);
        } else if (user == "관리자") {
          lilist.push(<Rli text={content} date={date} />);
        }
      });
      return lilist;
    })
    .then((list) => {
      ReactDOM.createRoot(document.querySelector(".chat-list")).render(list);
    })
    .then(() => {
      setTimeout(() => {
        // document.querySelector(".chats").scrollTop = document.querySelector(".chats").scrollHeight;
        $(".chats").animate(
          { scrollTop: document.querySelector(".chats").scrollHeight },
          500
        );
      }, 100);
    });
}

function reFlash() {
  fetch(`http://175.106.99.31/qna/admin/${no}`)
    .then((response) => response.json())
    .then((data) => {
      if (aciveText == data.data.content) {
        return;
      } else {
        aciveText = data.data.content;
      }
      let lilist = [];
      if (data.data == null) {
        return null;
      }
      data.data.content.split(",").forEach((text) => {
        let content = text.split(":")[0];
        let user = text.split(":")[1];
        let date = text.split(":")[2];
        if (user == "질문자") {
          lilist.push(<Lli text={content} date={date} />);
        } else if (user == "관리자") {
          lilist.push(<Rli text={content} date={date} />);
        }
      });
      return lilist;
    })
    .then((list) => {
      if (list == null) return;
      ReactDOM.createRoot(document.querySelector(".chat-list")).render(list);
    });
}

setTimeout(() => {
  setInterval(() => {
    reFlash();
  }, 1000);
}, 1000);

document.addEventListener("keydown", (event) => {
  if (event.key == "Enter" && $(".chat-text").val().length > 1) {
    fetch("http://175.106.99.31/qna/admin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        // 스프링에 전달할 값
        content: $(".chat-text").val(),
        mno: Number(no),
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.status == "success") {
          $(".chat-text").val("");
          reflash();
        }
      });
  }
});
