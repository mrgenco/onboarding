import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import React from 'react';
import ReactMarkdown from 'react-markdown';

interface MarkdownPreviewProps {
    markdown: string;
}

export default function Preview(props: MarkdownPreviewProps) {
    return (
        <Card variant="outlined">
            <CardContent>
                <ReactMarkdown>{props.markdown}</ReactMarkdown> 
            </CardContent>
        </Card>
    );
};
