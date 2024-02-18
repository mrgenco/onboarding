import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Grid, Card, CardContent, Typography, CardActionArea, Button } from '@mui/material';

interface ListItem {
  uuid: string;
  title: string;
  summary: string;
  createdBy: number;
  createdTime: string;
  lastUpdatedBy: number;
  lastUpdatedTime: string;
  status: number;
}

export default function DashboardPage() {
  const [listData, setListData] = useState<ListItem[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get<ListItem[]>('http://localhost:8080/document/list');
        setListData(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  const handleStartReading = (uuid: string) => {
    // Add your logic for handling the "Start Reading" action here
    console.log(`Start reading ${uuid}`);
  };

  return (
    <Grid container spacing={3}>
      {listData.map((item: ListItem) => (
        <Grid key={item.uuid} item xs={12} sm={6} md={4} lg={3}>
          <Card>
            <CardContent>
              <Typography variant="h6" component="h2">
                {item.title}
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                {item.summary}
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                Created by: {item.createdBy}
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                Created Time: {item.createdTime}
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                Last Updated By: {item.lastUpdatedBy}
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                Last Updated Time: {item.lastUpdatedTime}
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                Status: {item.status}
              </Typography>

              <Button onClick={() => handleStartReading(item.uuid)} variant="outlined" color="primary">
                Start Reading
              </Button>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
};