var React = require('react');
var underscored = require('underscore.string/underscored');

// create the  namespace
var ReactRailsUJS = {
    CLASS_NAME_ATTR: 'data-react-class',
    PROPS_ATTR: 'data-react-props',
    RAILS_ENV_DEVELOPMENT: false,
    // helper method for the mount and unmount methods to find the
    // `data-react-class` DOM elements
    findDOMNodes: function () {
        // we will use fully qualified paths as we do not bind the callbacks
        var selector = '[' + ReactRailsUJS.CLASS_NAME_ATTR + ']';

        return document.querySelectorAll(selector);
    },

    mountComponents: function () {
        var nodes = ReactRailsUJS.findDOMNodes();

        for (var i = 0; i < nodes.length; ++i) {
            var node = nodes[i];
            var className = node.getAttribute(ReactRailsUJS.CLASS_NAME_ATTR);
            var dirName = className.toLowerCase();

            // Assume className is simple and can be found at top-level (window).
            // Fallback to eval to handle cases like 'My.React.ComponentName'.

            /*
             require('bundle?lazy!./assets.components/' + dirName + '/index.js')(function (constructor) {
             var propsJson = node.getAttribute(ReactRailsUJS.PROPS_ATTR);
             var props = propsJson && JSON.parse(propsJson);

             React.render(React.createElement(constructor, props), node);
             });
             */

            var constructor = require('./' + dirName + '/index.js');
            var propsJson = node.getAttribute(ReactRailsUJS.PROPS_ATTR);
            var props = propsJson && JSON.parse(propsJson);

            React.render(React.createElement(constructor, props), node);

            /*
             require.ensure([], function(require) {
             var constructor = require('./assets.components/' + dirName + '/index.js');
             var propsJson = node.getAttribute(ReactRailsUJS.PROPS_ATTR);
             var props = propsJson && JSON.parse(propsJson);

             React.render(React.createElement(constructor, props), node);
             });
             */
        }
    },

    unmountComponents: function () {
        var nodes = ReactRailsUJS.findDOMNodes();

        for (var i = 0; i < nodes.length; ++i) {
            var node = nodes[i];

            React.unmountComponentAtNode(node);
            // now remove the `data-react-class` wrapper as well
            node.parentElement && node.parentElement.removeChild(node);
        }
    }
};

// functions not exposed publicly
function handleTurbolinksEvents() {
    var handleEvent;
    var unmountEvent;

    handleEvent = function (eventName, callback) {
        document.addEventListener(eventName, callback);
    };

    if (Turbolinks.EVENTS) {
        unmountEvent = Turbolinks.EVENTS.BEFORE_UNLOAD;
    } else {
        unmountEvent = 'page:receive';
        Turbolinks.pagesCached(0);

        if (ReactRailsUJS.RAILS_ENV_DEVELOPMENT) {
            console.warn('The Turbolinks cache has been disabled (Turbolinks >= 2.4.0 is recommended). See https://github.com/reactjs/react-rails/issues/87 for more information.');
        }
    }
    handleEvent('page:change', ReactRailsUJS.mountComponents);
    handleEvent(unmountEvent, ReactRailsUJS.unmountComponents);
}

function handleNativeEvents() {

    document.addEventListener('DOMContentLoaded', ReactRailsUJS.mountComponents);
    addEventListener('unload', ReactRailsUJS.unmountComponents);
}

if (typeof Turbolinks !== 'undefined' && Turbolinks.supported) {
    handleTurbolinksEvents();
} else {
    handleNativeEvents();
}

module.exports = ReactRailsUJS;