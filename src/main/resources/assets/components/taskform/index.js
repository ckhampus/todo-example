import React from 'react';

var TaskForm = React.createClass({
    handleSubmit: function (event) {
        event.preventDefault();
        var description = React.findDOMNode(this.refs.description).value.trim();
        if (!description) {
            return;
        }
        this.props.onTaskSubmit({
            description: description,
            completed: false
        });
        React.findDOMNode(this.refs.description).value = '';
        return;
    },
    render: function () {
        return (
            <form className="taskForm" onSubmit={this.handleSubmit}>
                <input type="text" ref="description" placeholder="What to do?" />
                <input type="submit" value="Post" />
            </form>
        )
    }
});

export default TaskForm;