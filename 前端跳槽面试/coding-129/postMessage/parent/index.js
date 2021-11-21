window.addEventListener('message', function(data) {
  console.log('我是父窗口，收到了子窗口发过来的消息', data)
}, false)

var btn = document.querySelector('#send');
btn.addEventListener('click', function(e) {
  let child = document.querySelector('iframe').contentWindow;
  child.postMessage('这是父窗口发给你的消息', 'http://localhost:9001')
}, false)
