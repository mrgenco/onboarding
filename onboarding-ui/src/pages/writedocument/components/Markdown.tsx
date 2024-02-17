import Paper from '@mui/material/Paper';
import TextField from '@mui/material/TextField';
import React, { useState, ChangeEvent } from 'react';

interface MarkdownEditorProps {
    onChange: (markdown: string) => void;
}

export default function MarkdownEditor(props: MarkdownEditorProps) {
    const [markdown, setMarkdown] = useState('');

    const handleInputChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
        const newMarkdown = event.target.value;
        setMarkdown(newMarkdown);
        props.onChange(newMarkdown);
    };

    return (
        <Paper> 

            <TextField
                id="filled-multiline-static"
                label="Write Your Document Here"
                multiline
                value={markdown}
                onChange={handleInputChange}
                fullWidth
                defaultValue="test"
            />

        </Paper>
    );
};