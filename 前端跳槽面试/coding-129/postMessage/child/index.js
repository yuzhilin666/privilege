window.addEventListener('message', function (data) {
    console.log(data)
    window.parent.postMessage('子窗口给父窗口的回显', 'http://localhost:9002')
}, false)
