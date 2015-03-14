var React = require('react');

module.exports = function (name, props, writer) {
    var dirName = name.toLowerCase();
    var constructor = require('./' + dirName + '/index.js');
    var html = React.renderToString(React.createElement(constructor, props));
    writer.write(html, 0, html.length);
}