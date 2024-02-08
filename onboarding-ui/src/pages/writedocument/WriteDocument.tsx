import * as React from 'react';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));

export default function WriteDocument() {
    return (
        <div>
            <Grid item xs={6}>
                <Item>test</Item>
            </Grid>
            <Grid item xs={6}>
                <Item>test2</Item>
            </Grid>
        </div>
    );
}