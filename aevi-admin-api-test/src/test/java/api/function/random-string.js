function(length) {
    let text = "";
    const possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    for (var i = 0; i < length; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}